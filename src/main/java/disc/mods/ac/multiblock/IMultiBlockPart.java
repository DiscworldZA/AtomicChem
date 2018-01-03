package disc.mods.ac.multiblock;

import disc.mods.ac.multiblock.MultiBlock.MultiBlockPartState;

public interface IMultiBlockPart {

	boolean clearMultiBlock();

	void setMultiBlockPartState(MultiBlockPartState state);

	MultiBlockPartState getMultiBlockPartState();

	default boolean isFormed() {
		return !(getMultiBlockPartState() == MultiBlockPartState.Invalid);
	}

	boolean formMultiBlock(MultiBlockPartState state);
}
