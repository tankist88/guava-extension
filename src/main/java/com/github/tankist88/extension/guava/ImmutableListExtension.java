package com.github.tankist88.extension.guava;

import com.github.tankist88.object2source.dto.ProviderInfo;
import com.github.tankist88.object2source.extension.collections.AbstractCollectionExtension;

import java.util.ArrayList;
import java.util.Set;

import static com.github.tankist88.object2source.util.GenerationUtil.getClassHierarchyStr;
import static com.github.tankist88.object2source.util.GenerationUtil.getInstName;


public class ImmutableListExtension extends AbstractCollectionExtension {
    @Override
    public boolean isTypeSupported(Class<?> clazz) {
        return getClassHierarchyStr(clazz).contains("com.google.common.collect.ImmutableList");
    }

    @Override
    public void fillMethodBody(StringBuilder bb, Set<ProviderInfo> providers, int objectDepth, Object obj) throws Exception {
        bb.append(getTabSymb())
          .append(getTabSymb());

        if(objectDepth > 1) {
            createAbstractCollectionInstance(obj, bb, providers, ArrayList.class, objectDepth);
            bb.append("return com.google.common.collect.ImmutableList.copyOf(")
              .append(getInstName(ArrayList.class))
              .append(");\n");
        } else {
            bb.append("return com.google.common.collect.ImmutableList.copyOf(new java.util.ArrayList());\n");
        }
    }

    @Override
    public String getActualType(Object obj) {
        return "com.google.common.collect.ImmutableList";
    }
}