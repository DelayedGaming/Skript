/**
 *   This file is part of Skript.
 *
 *  Skript is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Skript is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Skript.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright Peter Güttinger, SkriptLang team and contributors
 */
package ch.njol.skript.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.RequiredPlugins;
import ch.njol.skript.doc.Since;

@Name("Has Enchantment Glint Override")
@Description("Checks whether an item has enchantment glint override set.")
@Examples({
	"if {_item} has enchantment glint override:",
		"\tsend \"%{_item}% has enchantment glint override\" to player"
})
@RequiredPlugins("Spigot 1.20.5+")
@Since("INSERT VERSION")
public class CondItemHasEnchantmentGlint extends PropertyCondition<ItemType> {

	static {
		if (Skript.isRunningMinecraft(1, 20, 5))
			register(CondItemHasEnchantmentGlint.class, PropertyType.HAVE, "enchant[ment] glint overrid(e|den)", "itemtypes");
	}

	@Override
	public boolean check(ItemType itemType) {
		return itemType.getItemMeta().hasEnchantmentGlintOverride();
	}

	@Override
	protected String getPropertyName() {
		return "enchantment glint override";
	}

}
