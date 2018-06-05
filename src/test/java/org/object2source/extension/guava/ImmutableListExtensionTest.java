package org.object2source.extension.guava;

import com.google.common.collect.ImmutableList;
import org.object2source.SourceGenerator;
import org.object2source.dto.ProviderResult;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ImmutableListExtensionTest {
    @Test
    public void immutableListExtensionTest() {
        List<Integer> intList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            intList.add(i);
        }
        SourceGenerator sg = new SourceGenerator();
        sg.registerExtension(new ImmutableListExtension());
        ProviderResult pr = sg.createDataProviderMethod(ImmutableList.copyOf(intList));

        assertTrue(pr.getEndPoint().getMethodBody().contains("public static com.google.common.collect.ImmutableList getRegularImmutableList_"));
        assertTrue(pr.getEndPoint().getMethodBody().contains("java.util.ArrayList _arrayList = new java.util.ArrayList();"));
        for (int i = 0; i < 10; i ++) {
            assertTrue(pr.getEndPoint().getMethodBody().contains("_arrayList.add(" + i + ");"));
        }
        assertTrue(pr.getEndPoint().getMethodBody().contains("return com.google.common.collect.ImmutableList.copyOf(_arrayList);"));
    }
}
