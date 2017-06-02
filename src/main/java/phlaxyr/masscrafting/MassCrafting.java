package phlaxyr.masscrafting;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import phlaxyr.masscrafting.proxy.CommonProxy;

@Mod(modid = MassCrafting.MODID, name=MassCrafting.NAME, version = MassCrafting.VERSION)
public class MassCrafting
{
    public static final String MODID = "masscrafting";
    public static final String VERSION = "1.0";
    public static final String NAME = "Mass Crafting";
    
    @SidedProxy(clientSide="phlaxyr.masscrafting.proxy.ClientProxy",
    		serverSide="phlaxyr.masscrafting.proxy.ServerProxy")
    public static CommonProxy proxy;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	proxy.preInit();
    }
    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
    }
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	proxy.postInit();
    }
    public static String prependModID(String name) {
    	return MODID+":"+name;
    }
}
