package fr.iutvalence.info.dut.m3105.gildedroseinn.refactoring;

import java.util.ArrayList;
import java.util.List;

public class GildedRose {

	private static final int AVERAGE_SELL_IN = 11;
	private static final int MINIMUM_SELL_IN = 6;
	private static final int MINIMUM_QUALITY = 50;
	private static List<Item> items = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("OMGHAI!");

		items = new ArrayList<Item>();
		items.add(new Item("+5 Dexterity Vest", 10, 20));
		items.add(new Item("Aged Brie", 2, 0));
		items.add(new Item("Elixir of the Mongoose", 5, 7));
		items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
		items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
		items.add(new Item("Conjured Mana Cake", 3, 6));

		updateQualityAndSellInForAllItems();
	}

	public static void updateQualityAndSellInForAllItems() {
		items.parallelStream().filter(e -> !isLegendary(e)).forEach(GildedRose::updateAndSellInForNonLegenday);
	}

	private static void updateAndSellInForNonLegenday(Item item) {
		updateQuality(item);
		updateSellInForItem(item);
	}
	private static boolean isLegendary(Item item) {
		return item.getName().equals("Sulfuras, Hand of Ragnaros");
	}

	private static void updateQuality(Item item) {
		switch (item.getName()) {
		case "Aged Brie":
			updateQualityAgedBrie(item);
			break;

		case "Backstage passes to a TAFKAL80ETC concert":
			updateQualityBackstage(item);
			break;

		case "Sulfuras, Hand of Ragnaros":
			updateQualitySulfuras(item);
			break;

		default:
			updateQualitySulfuras(item);
			break;

		}
	}

	private static void updateQualitySulfuras(Item item) {
		if (item.getQuality() > 0)
			item.setQuality(item.getQuality() - 1);
	}

	private static void updateQualityBackstage(Item item) {
		if (item.getQuality() < MINIMUM_QUALITY)
			item.setQuality(item.getQuality() + 1);

		if (item.getSellIn() < AVERAGE_SELL_IN)
			item.setQuality(item.getQuality() + 1);

		if (item.getSellIn() < MINIMUM_SELL_IN)
			item.setQuality(item.getQuality() + 1);
		
		if (item.getSellIn() < 0)
			item.setQuality(0);

	}

	private static void updateQualityAgedBrie(Item item) {
		if (item.getQuality() < MINIMUM_QUALITY) {
			if (item.getSellIn() < 0)
				item.setQuality(item.getQuality() + 1);
			item.setQuality(item.getQuality() + 1);
		}
	}

	private static void updateSellInForItem(Item item) {
		if (!"Sulfuras, Hand of Ragnaros".equals(item.getName())) {
			item.setSellIn(item.getSellIn() - 1);
		}
	}

}