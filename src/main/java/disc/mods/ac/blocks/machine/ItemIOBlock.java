package disc.mods.ac.blocks.machine;

import com.mojang.realmsclient.dto.WorldDownload;

import disc.mods.ac.creativetab.Tabs;
import disc.mods.ac.tile.machine.TileEntityItemIO;
import disc.mods.core.block.CoreTileEntityBlock;
import disc.mods.core.tile.CoreTileEntity;
import disc.mods.core.util.EnumSide;
import disc.mods.core.util.InventoryHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemIOBlock extends CoreTileEntityBlock {

	public ItemIOBlock() {
		super("itemio", "machine/itemio");
		this.setTileEntity(TileEntityItemIO.class);
		this.setCreativeTab(Tabs.BlockTab);
	}

	@Override
	public boolean canRotate() {
		return true;
	}

	@Override
	public boolean canRotateVertically() {
		return true;
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (hand == EnumHand.MAIN_HAND) {
			TileEntityItemIO itemTe = (TileEntityItemIO) worldIn.getTileEntity(pos);
			playerIn.setItemStackToSlot(EntityEquipmentSlot.MAINHAND,
					InventoryHelper.Add(itemTe, playerIn.getHeldItemMainhand()));
			return true;
		}
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}

}
