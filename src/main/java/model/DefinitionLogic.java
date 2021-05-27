package model;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DefinitionLogic {

    public List<String> getAllModelProviders(Definition def) {
        final Map<String, Object> modules = def.getModules();
        if (Objects.isNull(modules)) {
            return null;
        }
        Object config = modules.get("riskModels");
        if (Objects.nonNull(config)) {
            if (true) {
                return (List) config;
            }
        }
        return null;
    }
}
