package org.object2source.extension.guava;


import org.object2source.dto.ProviderInfo;
import org.object2source.extension.collections.AbstractCollectionExtension;

import java.util.ArrayList;
import java.util.Set;

import static org.object2source.util.GenerationUtil.getClassHierarchyStr;
import static org.object2source.util.GenerationUtil.getInstName;

public class ImmutableListExtension extends AbstractCollectionExtension {
    @Override
    public boolean isTypeSupported(Class<?> clazz) {
        return getClassHierarchyStr(clazz).contains("com.google.common.collect.ImmutableList");
    }

    @Override
    public void fillMethodBody(StringBuilder bb, Set<ProviderInfo> providers, int objectDepth, Object obj) throws Exception {
        createAbstractCollectionInstance(obj, bb, providers, ArrayList.class, objectDepth);
        bb.append(getTabSymb())
          .append(getTabSymb())
          .append("return com.google.common.collect.ImmutableList.copyOf(")
          .append(getInstName(ArrayList.class))
          .append(");\n");
    }

    @Override
    public String getActualType(Object obj) {
        return "com.google.common.collect.ImmutableList";
    }
}
