package com.ooml.codegen.models.nodes.leafs;

import com.ooml.codegen.utils.ULogger;
import com.ooml.codegen.models.Leaf;

abstract class LAccessModifier extends Leaf {

	public LAccessModifier(String value) {
		super(value.replace(":", ""));
	}

	public abstract String getValueForJava();

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

	protected static class Default extends LAccessModifier {

		public Default() {
			super("");
		}

		@Override
		public String getValueForJava() {
			return this.getValue();
		}

	}

	protected static class Private extends LAccessModifier {

		public Private() {
			super("-");
		}

		@Override
		public String getValueForJava() {
			return "private";
		}

	}

	protected static class Protected extends LAccessModifier {

		public Protected() {
			super("#");
		}

		@Override
		public String getValueForJava() {
			return "protected";
		}

	}

	protected static class Public extends LAccessModifier {

		public Public() {
			super("+");
		}

		@Override
		public String getValueForJava() {
			return "public";
		}

	}

}