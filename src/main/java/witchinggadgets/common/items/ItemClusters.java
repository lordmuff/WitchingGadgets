package witchinggadgets.common.items;

import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.Level;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraftforge.oredict.OreDictionary;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.client.ClientUtilities;
import witchinggadgets.common.WGConfig;
import witchinggadgets.common.WGContent;

public class ItemClusters extends Item
{
	public static String[] subNames = {

			// TCon
			"Cobalt", "Ardite",
			// Factorization
			"FZDarkIron",
			// Metallurgy
			"Ignatius", "ShadowIron", "Lemurite", "Midasium", "Vyroxeres", "Ceruclase",
			"Alduorite", "Kalendrite", "Vulcanite", "Sanguinite", "Prometheum", "DeepIron", "Infuscolium", "Oureclase",
			"AstralSilver", "Carmot", "Mithril", "Rubracium", "Orichalcum", "Adamantine", "Atlarus", "Eximite",
			"Meutoite",
			// Gregtech
			"Cassiterite", "Garnierite", "Uraninite",
			"Magnetite", "BasalticMineralSand", "GraniticMineralSand", "Realgar",
			"Cinnabar","Molybdenite","Sphalerite","Stibnite","Pentlandite","Chalcopyrite","Arsenopyrite","Cobaltite","Galena","Cooperite",
			"Tetrahedrite","Kesterite","Stannite","Barite","Celestine","Scheelite","Wolframite","Ferberite","Huebnerite","Tungstate",
			"Stolzite","Russellite","Pinalite","Wollastonite","Zeolite","Pollucite","BrownLimonite","YellowLimonite","VanadiumMagnetite",
			"Tantalite", "Columbite","Coltan","Ilmenite","Bauxite","Chromite","Powellite","Wulfenite","Bastnasite","Pitchblende","Malachite",
			"Bromargyrite","Smithsonite","Sperrylite","Perlite","Trona","Mirabilite","Bischofite","Borax","Diatomite","Spodumene","Lepidolite",
			"Glauconite","Vermiculite","Mica","Kyanite","Alunite","GarnetSand","QuartzSand","DiduraniumTrioxide","DuraniumHexafluoride",
			"DuraniumHexachloride","DuraniumHexabromide","DuraniumHexaiodide","DuraniumHexaastatide","TritaniumDioxide","TritaniumHexafluoride",
			"TritaniumHexachloride","TritaniumHexabromide","TritaniumHexaiodide","TritaniumHexaastatide",

			"Electrum","SterlingSilver","RoseGold","Angmallen","GoldInductive","CdInAgAlloy","GildedIron","Brass","CobaltBrass","AluminiumAlloy",
			"Bronze","BlackBronze","BismuthBronze","Hepatizon","ArsenicCopper","ArsenicBronze","Steel","BlackSteel","BlueSteel","RedSteel","DamascusSteel",
			"VanadiumSteel","Tungstensteel","TungstenCarbide","HSLASteel","HSLASpringSteel","HSLATungstenAlloy","PigIron","IronCompressed","CastIron","IronMagnetic",
			"SteelMagnetic","NeodymiumMagnetic","SteelGalvanized","TungstenSintered","Titanium-Gold","TantalumHafniumCarbide","MeteoricIron","MeteoricSteel","MeteoricBlackSteel",
			"MeteoricBlueSteel","MeteoricRedSteel","RedAlloy","BlueAlloy","PurpleAlloy","Mingrade","RedstoneAlloy","NikolineAlloy","ElectrotineAlloy","ElectrumFlux","ConductiveIron",
			"EnergeticSilver","Invar","Constantan","Nichrome","Kanthal","Magnalium","StainlessSteel","Ultimet","TinAlloy","BatteryAlloy","SolderingAlloy","Ironwood","Steeleaf","Knightmetal",
			"FierySteel","Fireleaf","MeteoflameSteel","MeteoflameBlackSteel","MeteoflameBlueSteel","MeteoflameRedSteel","Thaumium","DarkThaumium","Osmiridium","Sunnarium",
			"VibraniumSteel","VibraniumSilver","Vibramantium","Signalum","Lumium","EnderiumBase","Enderium","GlowstoneRefined","ObsidianRefined","BedrockHSLAAlloy","ObsidianSteel",
			"PulsatingIron","EnergeticAlloy","VibrantAlloy","ElectricalSteel","Soularium","EndSteel","MelodicAlloy","StellarAlloy","VividAlloy","CrystallineAlloy",
			"Manasteel","Terrasteel","Elementium","GaiaSpirit","Endium","Ancient Debris","Netherite","Netherized Diamond","Efrine","Desh", "WorkersAlloy","Duranium",
			"Tritanium","Dolamide","HSS-G","HSS-E","HSS-S","Bedrockium","Draconium","DraconiumAwakened","Trinaquadalloy","Trinitanium","Adamantium","Naquadah","NaquadahEnriched","Naquadria",
			"Vibranium","DuraniumElemental","TritaniumElemental","Trinium","Uranium233","Uranium235","Uranium238","Thorium232","Plutonium","PlutoniumRG","Plutonium238","Plutonium239","Plutonium240",
			"Plutonium241","Americium241","Americium242","AmericiumRG","Neptunium237","Polonium210","Technetium99","Radium226","Radium","Actinium227","Cobalt60","Gold198","Lead209","Schrabidium","Solinium",
			"Schrabidate","Schraranium","Ghiorsium336","Curium242","Curium243","Curium244","Curium245","Curium246","Curium247","CuriumRG","CuriumFuel","Berkelium247","Californium251","Californium252",
			"Einsteinium253","Einsteinium255","AmericiumFuel","AdvancedAlloy","TcAlloy","CdAlloy","DuraSteel","Polymer","Bakelite","PET","Polycarbonate","PVC","MagnetizedTungsten","CMBSteel","Starmetal",
			"Saturnite","Ferrouranium","Euphemium","Dineutronium","Australium","Lithium","Lithium-6","Beryllium","Beryllium-7","Beryllium-8","Boron","Boron-11","Carbon","Sodium","Magnesium","Aluminium",
			"Silicon","Titanium","Vanadium","Chromium","Manganese","Iron","Cobalt","Nickel","Copper","Zinc","Gallium","Germanium","Rubidium","Strontium","Yttrium","Zirconium","Niobium","Molybdenum",
			"Ruthenium","Rhodium","Palladium","Silver","Cadmium","Indium","Tin","Antimony","Tellurium","Caesium","Barium","Lanthanium","Cerium","Praseodymium","Neodymium","Promethium","Samarium",
			"Europium","Gadolinium","Terbium","Dysprosium","Holmium","Erbium","Thulium","Ytterbium","Lutetium","Hafnium","Tantalum","Tungsten","Rhenium","OsmiumElemental","Iridium","Platinum","Gold","Mercury",
			"Thallium","Lead","Farnsium","Shadow"

	};
	public static HashMap<String, Integer[]> materialMap = new HashMap();

	IIcon iconMetal;
	IIcon[] iconOverlay = new IIcon[3];

	public ItemClusters()
	{
		super();
		maxStackSize = 64;
		setCreativeTab(WitchingGadgets.tabWG);
		setHasSubtypes(true);
	}

	@Override
	public int getColorFromItemStack(ItemStack stack, int pass)
	{
		if(pass==0)
		{
			if(materialMap.get( subNames[stack.getItemDamage()])!=null)
				return materialMap.get( subNames[stack.getItemDamage()] )[0];
		}
		return 0xffffff;
	}

	public static ItemStack getCluster(String ore)
	{
		if(WGConfig.allowClusters)
			for(int sn=0; sn<subNames.length; sn++)
				if(subNames[sn].equalsIgnoreCase(ore))
					return new ItemStack(WGContent.ItemCluster,1,sn);
		return null;
	}

	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		this.iconMetal = iconRegister.registerIcon("witchinggadgets:cluster_metal");
		this.iconOverlay[0] = iconRegister.registerIcon("witchinggadgets:cluster_overlay");
		this.iconOverlay[1] = iconRegister.registerIcon("witchinggadgets:cluster_overlayNether");
		this.iconOverlay[2] = iconRegister.registerIcon("witchinggadgets:cluster_overlayEnd");
	}
	@Override
	public boolean requiresMultipleRenderPasses()
	{
		return true;
	}

	@Override
	public IIcon getIconFromDamageForRenderPass(int damage, int pass)
	{
		if(pass==0)
			return this.iconMetal;
		else if(materialMap.get(subNames[damage])!=null)
			return this.iconOverlay[ materialMap.get(subNames[damage])[1] ];
		else
			return this.iconOverlay[0];
	}
	@Override
	public IIcon getIcon(ItemStack stack, int pass)
	{
		return getIconFromDamageForRenderPass(stack.getItemDamage(), pass);
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack)
	{
		String ss = "";
		if(!OreDictionary.getOres("ingot"+subNames[stack.getItemDamage()]).isEmpty())
		{
			ItemStack ingot = OreDictionary.getOres("ingot"+subNames[stack.getItemDamage()]).get(0);
			int limit = ingot.getDisplayName().lastIndexOf(" ");
			ss = ingot.getDisplayName().substring(0, Math.max(0, limit));
		}
	    return StatCollector.translateToLocalFormatted(this.getUnlocalizedNameInefficiently(stack)+".name",ss).trim();
	}
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List itemList)
	{
		if(WGConfig.allowClusters)
			for(int iOre=0; iOre<subNames.length; iOre++)
				if(!OreDictionary.getOres("ore"+subNames[iOre]).isEmpty() && !OreDictionary.getOres("ingot"+subNames[iOre]).isEmpty())
					itemList.add( new ItemStack(item,1,iOre) );
	}

	public static void setupClusters()
	{
		if(WGConfig.allowClusters)
			for(String ore : subNames)
				if(!OreDictionary.getOres("ore"+ore).isEmpty() && !OreDictionary.getOres("ingot"+ore).isEmpty())
				{
					try{
						List<Integer> colList = ClientUtilities.getItemColours( OreDictionary.getOres("ore"+ore).get(0) );
						if(!colList.isEmpty())
						{
							int oreBlockColour = colList.get(0);
							int[] rgb = {oreBlockColour>>16&0xff, oreBlockColour>>8&0xff, oreBlockColour&0xff};
							int clustertype = rgb[0]>rgb[2]&&rgb[1]>rgb[2]?2 :rgb[0]>rgb[1]&&rgb[0]>rgb[2]?1 : 0;
							List<Integer> colours = ClientUtilities.getItemColours(OreDictionary.getOres("ingot"+ore).get(0));

							int colour = ClientUtilities.getVibrantColourToInt(colours.get((int)(colours.size()*.65)));
							colour = ClientUtilities.getVibrantColourToInt(colour);

							materialMap.put(ore, new Integer[]{colour, clustertype} );
						}
					}catch(Exception e)
					{
						WitchingGadgets.logger.log(Level.ERROR, "Error setting up cluster for "+ore);
					}
				}
	}
}	