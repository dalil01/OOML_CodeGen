package com.ooml.codegen.models.nodes.leafs;

import com.ooml.codegen.utils.ULogger;
import com.ooml.codegen.models.Leaf;

public abstract class LAccessModifier extends Leaf {

	public LAccessModifier(String value) {
		super(value.replace(":", ""));
	}

	public LAccessModifier findModifierFromSignValue() {
		return switch (this.getValue()) {
			case "" -> new Default();
			case "+" -> new Public();
			case "-" -> new Private();
			case "#" -> new Protected();
			default -> {
				// TODO error
				ULogger.error("invalid accessModifier");
				throw new IllegalStateException("Unexpected value: " + this.getValue());
			}
		};
	}

	public static class Default extends LAccessModifier {

		public Default() {
			super("");
		}

	}

	public static class Private extends LAccessModifier {

		public Private() {
			super("-");
		}

	}

	public static class Protected extends LAccessModifier {

		public Protected() {
			super("#");
		}

	}

	public static class Public extends LAccessModifier {

		public Public() {
			super("+");
		}

	}

}