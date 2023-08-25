package net.agrotom.tccompat.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.function.Supplier;

import javax.annotation.Nonnull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

import net.agrotom.tccompat.data.materials.Materials;
import net.agrotom.tccompat.data.materials.extra.IExtraData;
import net.agrotom.tccompat.data.materials.extra.IMaterialIngredient;

/**
 * Auto generating materials data files like a fluids, buckets, stats etc.
 * @author agro_tom
 *
 */
public class DataGenerator {

	private String jarPath = ClassLoader.getSystemClassLoader().getResource(".").getPath();
	private String resourceDir = jarPath + "generated/";
	private boolean doesReplace = false;
	private Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	public DataGenerator() {}
	
	/**
	 * 
	 * @param resourceDir - it's better to provide your mod project's resource folder
	 * (use {@link #doesReplace} if afraid of loosing data).
	 * @param doesReplace - set to true if you want not to DataGenerator replace existing files.
	 * Recommend to do it if afraid of lost custom edited data files).
	 */
	public DataGenerator(@Nonnull String resourceDir, boolean doesReplace) {
		/*resourceDir = resourceDir.replaceAll("\\", "/");
		
		if (!resourceDir.endsWith("/")) {
			resourceDir = resourceDir + "/";
		}*/
		
		this.doesReplace = doesReplace;
		
		this.resourceDir = resourceDir;
	}
	
	public void generate(@Nonnull String modID) {
		Materials.MATERIALS.forEach(materialInstance -> {
			final String materialName = materialInstance.getID().getPath();
			final IExtraData materialData = materialInstance.getExtraData();
			final String materialColor = String.format("%1$06x", materialInstance.getColor());
			
			final String materialModID = materialInstance.getID().getNamespace();
			final String materialID = materialInstance.getID().toString();
			
			generateFile(materialName + "_bucket", "assets/" + modID + "/models/item/", () -> {
				JsonObject object = new JsonObject();
				
				object.addProperty("parent", "forge:item/bucket_drip");
				object.addProperty("loader", "forge:bucket");
				object.addProperty("fluid", materialModID + ":molten_" + materialName);
				
				return object;
			});
			
			generateFile(materialName, "assets/" + modID + "/models/tool_materials/", () -> {
				JsonObject object = new JsonObject();
				
				object.addProperty("fallback", "metal");
				object.addProperty("color", materialColor);
				
				return object;
			});
			
			generateFile(materialName, "data/" + modID + "/materials/definition/", () -> {
				JsonObject object = new JsonObject();
			
				final String materialDepndency = materialInstance.getModId();
				
				if (materialDepndency != null) {
					JsonObject conditions = new JsonObject();
					JsonArray values = new JsonArray();
				
				
					JsonObject configProp = new JsonObject();
					configProp.addProperty("prop", "force_integration_materials");
					configProp.addProperty("type", "tconstruct:config");
					
					JsonObject modCondition = new JsonObject();
					modCondition.addProperty("modid", materialDepndency);
					modCondition.addProperty("type", "forge:mod_loaded");
					
					values.add(configProp);
					values.add(modCondition);
					
					conditions.add("values", values);
					conditions.addProperty("type", "forge:or");
					
					
					object.add("condition", conditions);
				}
				
				object.addProperty("craftable", materialInstance.isCraftable());
				object.addProperty("tier", materialInstance.getTier());
				object.addProperty("sortOrder", materialInstance.getOrder());
				object.addProperty("textColor", "#" + materialColor.toUpperCase());
				object.addProperty("hidden", materialInstance.isHidden());
				
				return object;
			});
			
			generateFile(materialName, "data/" + modID + "/materials/stats/", () -> {
				JsonObject object = new JsonObject();
				JsonObject stats = new JsonObject();
				
				JsonObject head = new JsonObject();
				JsonObject handle = new JsonObject();
				JsonObject extra = new JsonObject();
				
				head.addProperty("durability", materialData.getHeadDurability());
				head.addProperty("miningSpeed", materialData.getHeadMiningSpeed());
				head.addProperty("harvestLevel", materialData.getHeadHarvestLevel());
				head.addProperty("attack", materialData.getHeadAttackDamage());
				
				handle.addProperty("durability", materialData.getHandleDurability());
				handle.addProperty("miningSpeed", materialData.getHandleMiningSpeed());
				handle.addProperty("attackSpeed", materialData.getHandleAttackSpeed());
				handle.addProperty("attackDamage", materialData.getHandleAttackDamage());
				
				extra.addProperty("durability", materialData.getExtraDurability());
				
				stats.add("tconstruct:head", head);
				stats.add("tconstruct:handle", handle);
				stats.add("tconstruct:extra", extra);
				
				object.add("stats", stats);
				
				return object;
			});
			
			generateFile(materialName, "data/" + modID + "/materials/traits/", () -> {
				JsonObject object = new JsonObject();
				JsonArray traits = new JsonArray();
				
				materialData.getModifiers().forEach(modifier -> {
					JsonObject traitJsonObject = new JsonObject();
					traitJsonObject.addProperty("name", modifier.getA());
					traitJsonObject.addProperty("level", modifier.getB());
					
					traits.add(traitJsonObject);
				});
				
				object.add("default", traits);
				
				return object;
			});
			
			generateFile("molten_" + materialName, "data/" + modID + "/tags/fluids/", () -> {
				JsonObject object = new JsonObject();
				JsonArray values = new JsonArray();
				
				values.add(modID + ":molten_" + materialName);
				values.add(modID + ":flowing_molten_" + materialName);
				
				object.addProperty("replace", false);
				object.add("values", values);
				
				return object;
			});
			
			generateFile("molten_" + materialName, "data/" + modID + "/tags/fluids/", () -> {
				JsonObject object = new JsonObject();
				JsonArray values = new JsonArray();
				
				values.add(modID + ":molten_" + materialName);
				values.add(modID + ":flowing_molten_" + materialName);
				
				object.addProperty("replace", false);
				object.add("values", values);
				
				return object;
			});
			
			final List<IMaterialIngredient> ingredients = materialData.getMaterialIngredients();

			if (!materialInstance.isCraftable()) {
				generateFile(materialName, "data/" + modID + "/recipes/tools/materials/casting/", () -> {
					JsonObject object = new JsonObject();
					
					JsonObject fluid = new JsonObject();
					
					fluid.addProperty("tag", modID + ":molten_" + materialName);
					fluid.addProperty("amount", 144);
					
					object.addProperty("type", "tconstruct:material_fluid");
					object.add("fluid", fluid);
					object.addProperty("temperature", 1000);
					object.addProperty("output", modID + ":" + materialName);
					
					return object;
				});
				
				generateFile(materialName, "data/" + modID + "/recipes/tools/materials/melting/", () -> {
					JsonObject object = new JsonObject();
					
					JsonObject result = new JsonObject();
					
					result.addProperty("fluid", modID + ":molten_" + materialName);
					result.addProperty("amount", 144);
					
					object.addProperty("type", "tconstruct:material_melting");
					object.addProperty("input", materialID);
					object.addProperty("temperature", 1000);
					object.add("result", result);
					
					return object;
				});
			}
			
			ingredients.forEach(ingredient -> {
				generateFile(ingredient.getName(), "data/" + modID + "/recipes/tools/materials/" + materialName + "/", () -> {
					JsonObject object = new JsonObject();
					JsonArray ingredientsData = new JsonArray();
					
					ingredient.getIngredients().forEach(itemIngredient -> {
						final JsonObject itemIngredientObject = new JsonObject();
						itemIngredientObject.addProperty(itemIngredient.getType().lowerCaseName(), itemIngredient.getName());
						
						ingredientsData.add(itemIngredientObject);
					});
					
					
					object.addProperty("type", "tconstruct:material");
					object.add("ingredient", ingredientsData);
					object.addProperty("value", ingredient.getValue());
					object.addProperty("needed", ingredient.getNeeded());
					object.addProperty("material", materialID);
					
					return object;
				});
			});
		});
		
	}
	
	private void generateFile(@Nonnull String fileName, @Nonnull String path, @Nonnull Supplier<JsonObject> jsonSupplier) {
		final String bakedPath = resourceDir + path;
		
		new File(bakedPath).mkdirs();
		
		write(jsonSupplier.get(), bakedPath + fileName + ".json");
	}
	
	private void write(@Nonnull JsonObject element, @Nonnull String path) {
		try {
			final FileWriter writer = new FileWriter(path);
			element.addProperty("_comment", "AUTO GENERATED");
			
			gson.toJson(element, writer);
			writer.flush();
			writer.close();
		} catch (JsonIOException | IOException e) {
			LOGGER.error("An error was occured while creating or writing JSON data file for material!");
			e.printStackTrace();
		}
	}
	
	private static final Logger LOGGER = LogManager.getLogger(DataGenerator.class);

}
