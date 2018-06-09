package org.object2source.extension.guava;

import com.google.common.collect.ImmutableList;
import org.object2source.SourceGenerator;
import org.object2source.dto.ProviderResult;
import org.object2source.extension.guava.data.TestClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ImmutableListExtensionTest {
    @Test
    public void immutableListExtensionTest() {
        SourceGenerator sg = new SourceGenerator();
        sg.registerExtension(new ImmutableListExtension());
        ProviderResult pr = sg.createDataProviderMethod(ImmutableList.copyOf(createTestList()));
        assertTrue(pr.getEndPoint().getMethodBody().contains("public static com.google.common.collect.ImmutableList getRegularImmutableList_"));
        assertTrue(pr.getEndPoint().getMethodBody().contains("java.util.ArrayList _arrayList = new java.util.ArrayList();"));
        assertTrue(pr.getEndPoint().getMethodBody().contains("_arrayList.add(getTestClass_"));
        assertTrue(pr.getEndPoint().getMethodBody().contains("return com.google.common.collect.ImmutableList.copyOf(_arrayList);"));
    }

    @Test
    public void immutableListExtensionLowDepthTest() {
        SourceGenerator sg = new SourceGenerator();
        sg.registerExtension(new ImmutableListExtension());
        sg.setMaxObjectDepth(2);
        sg.setExceptionWhenMaxODepth(false);
        ProviderResult pr = sg.createDataProviderMethod(ImmutableList.copyOf(createTestList()));
        assertTrue(pr.getEndPoint().getMethodBody().contains("public static com.google.common.collect.ImmutableList getRegularImmutableList_"));
        assertTrue(pr.getEndPoint().getMethodBody().contains("return com.google.common.collect.ImmutableList.copyOf(new java.util.ArrayList());"));
    }

    private List<TestClass> createTestList() {
        List<TestClass> intList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            intList.add(new TestClass(i));
        }
        return intList;
    }
}
