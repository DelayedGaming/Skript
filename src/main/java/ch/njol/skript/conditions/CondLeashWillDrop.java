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
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Events;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityUnleashEvent;
import org.bukkit.event.player.PlayerUnleashEntityEvent;
import org.jetbrains.annotations.Nullable;

@Name("Leash Will Drop")
@Description("Checks whether the leash will drop in an unleash event.")
@Examples({
	"on unleash:",
		"\tif the leash will drop:",
			"\t\tprevent the leash from dropping",
		"\telse if the leash will drop:",
			"\t\tallow the leash to drop"
})
@Events("Unleash")
@Since("INSERT VERSION")
public class CondLeashWillDrop extends Condition {

	static {
		Skript.registerCondition(CondLeashWillDrop.class, "[the] (lead|leash) will [:not] (drop|be dropped)");
	}

	@Override
	public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		if (!getParser().isCurrentEvent(PlayerUnleashEntityEvent.class, EntityUnleashEvent.class)) {
			Skript.error("The 'leash will drop' condition can only be used in an 'unleash' event.");
			return false;
		}
		setNegated(parseResult.hasTag("not"));
		return true;
	}

	@Override
	public boolean check(Event event) {
		if (!(event instanceof EntityUnleashEvent))
			return false;
		return ((EntityUnleashEvent) event).isDropLeash() ^ isNegated();
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		return "leash will" + (isNegated() ? " not " : " ") + "be dropped";
	}

}
