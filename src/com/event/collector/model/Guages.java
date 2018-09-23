package com.event.collector.model;

public class Guages {

        private String memoryHeapMax;
        private String memoryPoolsCompressedClassSpaceMax;
        private String memoryPoolsCompressedClassSpaceUsed;
        private String memoryPoolsPSEdenSpaceUsedfterGc;
        private String memoryNonHeapInit;

    public String getMemoryHeapMax() {
        return memoryHeapMax;
    }

    public void setMemoryHeapMax(String memoryHeapMax) {
        this.memoryHeapMax = memoryHeapMax;
    }

    public String getMemoryPoolsCompressedClassSpaceMax() {
        return memoryPoolsCompressedClassSpaceMax;
    }

    public void setMemoryPoolsCompressedClassSpaceMax(String memoryPoolsCompressedClassSpaceMax) {
        this.memoryPoolsCompressedClassSpaceMax = memoryPoolsCompressedClassSpaceMax;
    }

    public String getMemoryPoolsCompressedClassSpaceUsed() {
        return memoryPoolsCompressedClassSpaceUsed;
    }

    public void setMemoryPoolsCompressedClassSpaceUsed(String memoryPoolsCompressedClassSpaceUsed) {
        this.memoryPoolsCompressedClassSpaceUsed = memoryPoolsCompressedClassSpaceUsed;
    }

    public String getMemoryPoolsPSEdenSpaceUsedfterGc() {
        return memoryPoolsPSEdenSpaceUsedfterGc;
    }

    public void setMemoryPoolsPSEdenSpaceUsedfterGc(String memoryPoolsPSEdenSpaceUsedfterGc) {
        this.memoryPoolsPSEdenSpaceUsedfterGc = memoryPoolsPSEdenSpaceUsedfterGc;
    }

    public String getMemoryNonHeapInit() {
        return memoryNonHeapInit;
    }

    public void setMemoryNonHeapInit(String memoryNonHeapInit) {
        this.memoryNonHeapInit = memoryNonHeapInit;
    }
}
