package net.agrotom.tccompat.data.materials;

import javax.annotation.Nonnull;

import net.agrotom.tccompat.data.providers.MaterialDataProvider;
import net.agrotom.tccompat.data.providers.MaterialRecipes;
import net.agrotom.tccompat.data.providers.MaterialRenderInfo;
import net.agrotom.tccompat.data.providers.MaterialSpriteProvider;
import net.agrotom.tccompat.data.providers.MaterialStatsDataProvider;
import net.agrotom.tccompat.data.providers.MaterialTraitsDataProvider;

public interface IDataVisitors {
	
	public static final IDataVisitors empty = new IDataVisitors() {
		
		@Override
		public void accept(MaterialRenderInfo provider) {
			
		}
		
		@Override
		public void accept(MaterialRecipes provider) {
			
		}
		
		@Override
		public void accept(MaterialTraitsDataProvider provider) {
			
		}
		
		@Override
		public void accept(MaterialStatsDataProvider provider) {
			
		}
		
		@Override
		public void accept(MaterialSpriteProvider provider) {
			
		}
		
		@Override
		public void accept(MaterialDataProvider provider) {
			
		}

		@Override
		public IMaterialData getParent() {
			return null;
		}
		
	};
	
	IMaterialData getParent();
	
	void accept(@Nonnull MaterialDataProvider provider);
	void accept(@Nonnull MaterialSpriteProvider provider);
	void accept(@Nonnull MaterialStatsDataProvider provider);
	void accept(@Nonnull MaterialTraitsDataProvider provider);
	void accept(@Nonnull MaterialRecipes provider);
	void accept(@Nonnull MaterialRenderInfo provider);
	
}
