package com.darchan.logic.simulator.functional.intel8080;

import com.darchan.logic.simulator.functional.Components;
import com.darchan.logic.simulator.functional.Range;

public class Memory {

    private final boolean[][] memory;

    private Memory(boolean[][] memory) {
        this.memory = memory;
    }

    public boolean[][] getMemory() {
        return memory;
    }

    public static class Builder {

        private static final Range WORD_WIDTH = new Range(8, 8);

        private final boolean[][] memory;

        private int idx = 0;

        public Builder(Memory memory) {
            this.memory = memory.getMemory();
        }

        public Builder() {
            this.memory = new boolean[65536][8];
        }

        public Memory build() {
            return new Memory(this.memory);
        }

        public void addWord(boolean[] word) {
            this.memory[idx++] = Components.validateNotNullAndWidth(word, WORD_WIDTH);
        }

    }


}
