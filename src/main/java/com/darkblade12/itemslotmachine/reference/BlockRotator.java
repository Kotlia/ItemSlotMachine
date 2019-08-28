package com.darkblade12.itemslotmachine.reference;

import org.bukkit.Axis;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.*;
import org.bukkit.block.data.type.Door;

import java.util.ArrayList;
import java.util.List;

final class BlockRotator {

    private BlockRotator() {
    }

    static BlockData rotateClockwise90(BlockData blockData) {
        switch (blockData.getMaterial()) {
            case RAIL:
            case POWERED_RAIL:
            case DETECTOR_RAIL:
            case ACTIVATOR_RAIL:
                Rail rail = (Rail) blockData;
                switch (rail.getShape()) {
                    case ASCENDING_EAST:
                        rail.setShape(Rail.Shape.ASCENDING_SOUTH);
                        break;
                    case ASCENDING_WEST:
                        rail.setShape(Rail.Shape.ASCENDING_NORTH);
                        break;
                    case ASCENDING_NORTH:
                        rail.setShape(Rail.Shape.ASCENDING_EAST);
                        break;
                    case ASCENDING_SOUTH:
                        rail.setShape(Rail.Shape.ASCENDING_WEST);
                        break;
                    case EAST_WEST:
                        rail.setShape(Rail.Shape.NORTH_SOUTH);
                        break;
                    case NORTH_EAST:
                        rail.setShape(Rail.Shape.SOUTH_EAST);
                        break;
                    case SOUTH_EAST:
                        rail.setShape(Rail.Shape.SOUTH_WEST);
                        break;
                    case SOUTH_WEST:
                        rail.setShape(Rail.Shape.NORTH_WEST);
                        break;
                    case NORTH_WEST:
                        rail.setShape(Rail.Shape.NORTH_EAST);
                        break;
                    case NORTH_SOUTH:
                        rail.setShape(Rail.Shape.EAST_WEST);
                        break;
                }
                return rail;
            case ACACIA_BUTTON:
            case ACACIA_FENCE_GATE:
            case ACACIA_STAIRS:
            case ACACIA_TRAPDOOR:
            case WALL_SIGN:
            case ANVIL:
            case BIRCH_BUTTON:
            case BIRCH_FENCE_GATE:
            case BIRCH_STAIRS:
            case BIRCH_TRAPDOOR:
            case BLACK_BED:
            case BLUE_BED:
            case BRICK_STAIRS:
            case BROWN_BED:
            case CHEST:
            case CHIPPED_ANVIL:
            case COBBLESTONE_STAIRS:
            case COCOA:
            case COMPARATOR:
            case CYAN_BED:
            case DAMAGED_ANVIL:
            case DARK_OAK_BUTTON:
            case DARK_OAK_FENCE_GATE:
            case DARK_OAK_STAIRS:
            case DARK_OAK_TRAPDOOR:
            case DARK_PRISMARINE_STAIRS:
            case DISPENSER:
            case DROPPER:
            case ENDER_CHEST:
            case FURNACE:
            case GRAY_BED:
            case GREEN_BED:
            case HOPPER:
            case JACK_O_LANTERN:
            case JUNGLE_BUTTON:
            case JUNGLE_FENCE_GATE:
            case JUNGLE_STAIRS:
            case JUNGLE_TRAPDOOR:
            case LADDER:
            case LEVER:
            case LIGHT_BLUE_BED:
            case LIGHT_GRAY_BED:
            case LIME_BED:
            case MAGENTA_BED:
            case MOVING_PISTON:
            case NETHER_BRICK_STAIRS:
            case OAK_BUTTON:
            case OAK_FENCE_GATE:
            case OAK_STAIRS:
            case OAK_TRAPDOOR:
            case ORANGE_BED:
            case PINK_BED:
            case PISTON:
            case PISTON_HEAD:
            case PRISMARINE_BRICK_STAIRS:
            case PRISMARINE_STAIRS:
            case PUMPKIN:
            case PURPLE_BED:
            case PURPUR_STAIRS:
            case QUARTZ_STAIRS:
            case RED_SANDSTONE_STAIRS:
            case REDSTONE_TORCH:
            case REPEATER:
            case SANDSTONE_STAIRS:
            case SPRUCE_BUTTON:
            case SPRUCE_FENCE_GATE:
            case SPRUCE_STAIRS:
            case SPRUCE_TRAPDOOR:
            case STICKY_PISTON:
            case STONE_BRICK_STAIRS:
            case STONE_BUTTON:
            case TORCH:
            case TRAPPED_CHEST:
            case TRIPWIRE_HOOK:
            case WHITE_BED:
            case YELLOW_BED:
                Directional directional = (Directional) blockData;
                switch (directional.getFacing()) {
                    case EAST:
                        directional.setFacing(BlockFace.SOUTH);
                        break;
                    case WEST:
                        directional.setFacing(BlockFace.NORTH);
                        break;
                    case SOUTH:
                        directional.setFacing(BlockFace.WEST);
                        break;
                    case NORTH:
                        directional.setFacing(BlockFace.EAST);
                        break;
                    default:
                        break;
                }
                return directional;
            case ACACIA_DOOR:
            case BIRCH_DOOR:
            case DARK_OAK_DOOR:
            case JUNGLE_DOOR:
            case OAK_DOOR:
            case SPRUCE_DOOR:
            case IRON_DOOR:
                Door door = (Door) blockData;
                if (door.getHalf().equals(Door.Half.BOTTOM)) {
                    switch (door.getFacing()) {
                        case EAST:
                            door.setFacing(BlockFace.SOUTH);
                            break;
                        case WEST:
                            door.setFacing(BlockFace.NORTH);
                            break;
                        case SOUTH:
                            door.setFacing(BlockFace.WEST);
                            break;
                        case NORTH:
                            door.setFacing(BlockFace.EAST);
                            break;
                        default:
                            break;
                    }
                }
                return door;
            case SIGN:
            case SKELETON_SKULL:
            case PLAYER_HEAD:
            case CREEPER_HEAD:
            case ZOMBIE_HEAD:
                Rotatable rotatable = (Rotatable) blockData;
                switch (rotatable.getRotation()) {
                    case EAST_NORTH_EAST:
                        rotatable.setRotation(BlockFace.SOUTH_SOUTH_EAST);
                        break;
                    case EAST:
                        rotatable.setRotation(BlockFace.SOUTH);
                        break;
                    case EAST_SOUTH_EAST:
                        rotatable.setRotation(BlockFace.SOUTH_SOUTH_WEST);
                        break;
                    case SOUTH_EAST:
                        rotatable.setRotation(BlockFace.SOUTH_WEST);
                        break;
                    case SOUTH_SOUTH_EAST:
                        rotatable.setRotation(BlockFace.WEST_SOUTH_WEST);
                        break;
                    case SOUTH:
                        rotatable.setRotation(BlockFace.WEST);
                        break;
                    case SOUTH_SOUTH_WEST:
                        rotatable.setRotation(BlockFace.WEST_NORTH_WEST);
                        break;
                    case SOUTH_WEST:
                        rotatable.setRotation(BlockFace.NORTH_WEST);
                        break;
                    case WEST_SOUTH_WEST:
                        rotatable.setRotation(BlockFace.NORTH_NORTH_WEST);
                        break;
                    case WEST:
                        rotatable.setRotation(BlockFace.NORTH);
                        break;
                    case WEST_NORTH_WEST:
                        rotatable.setRotation(BlockFace.NORTH_NORTH_EAST);
                        break;
                    case NORTH_WEST:
                        rotatable.setRotation(BlockFace.NORTH_EAST);
                        break;
                    case NORTH_NORTH_WEST:
                        rotatable.setRotation(BlockFace.EAST_NORTH_EAST);
                        break;
                    case NORTH:
                        rotatable.setRotation(BlockFace.EAST);
                        break;
                    case NORTH_NORTH_EAST:
                        rotatable.setRotation(BlockFace.EAST_SOUTH_EAST);
                        break;
                    case NORTH_EAST:
                        rotatable.setRotation(BlockFace.SOUTH_EAST);
                        break;
                }
                return rotatable;
            case HAY_BLOCK:
            case OAK_LOG:
            case DARK_OAK_LOG:
            case BIRCH_LOG:
            case JUNGLE_LOG:
            case ACACIA_LOG:
            case SPRUCE_LOG:
                Orientable orientable = (Orientable) blockData;
                switch (orientable.getAxis()) {
                    case X:
                        orientable.setAxis(Axis.Z);
                        break;
                    case Z:
                        orientable.setAxis(Axis.X);
                        break;
                    default:
                        break;
                }
                return orientable;
            case BROWN_MUSHROOM_BLOCK:
            case RED_MUSHROOM_BLOCK:
            case VINE:
                MultipleFacing multipleFacing = (MultipleFacing) blockData;
                List<BlockFace> faces = new ArrayList<>(multipleFacing.getFaces());
                switch (faces.get(0)) {
                    case EAST:
                        multipleFacing.setFace(BlockFace.EAST, false);
                        multipleFacing.setFace(BlockFace.SOUTH, true);
                        break;
                    case WEST:
                        multipleFacing.setFace(BlockFace.WEST, false);
                        multipleFacing.setFace(BlockFace.NORTH, true);
                        break;
                    case SOUTH:
                        multipleFacing.setFace(BlockFace.SOUTH, false);
                        multipleFacing.setFace(BlockFace.WEST, true);
                        break;
                    case NORTH:
                        multipleFacing.setFace(BlockFace.NORTH, false);
                        multipleFacing.setFace(BlockFace.EAST, true);
                        break;
                    default:
                        break;
                }
                if (faces.size() > 1) {
                    // do something
                }
                return multipleFacing;
            default:
                break;
        }
        return blockData;
    }
}
