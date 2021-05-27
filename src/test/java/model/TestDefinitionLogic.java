package model;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TestDefinitionLogic {
    @Test
    public void testNullModules() {
        // Arrange
        Definition definition = new Definition();

        // Act
        DefinitionLogic definitionLogic = new DefinitionLogic();
        final List<String> allModelProviders = definitionLogic.getAllModelProviders(definition);

        // Assert
        Assert.assertNull(allModelProviders);
    }

    @Test
    public void testNonNullInOneValueInRiskModelModules() {
        // Arrange
        Definition definition = new Definition();
        final HashMap<String, Object> modules = new HashMap<>();
        modules.put("riskModels", Arrays.asList("PayU"));
        definition.setModules(modules);

        // Act
        DefinitionLogic definitionLogic = new DefinitionLogic();
        final List<String> allModelProviders = definitionLogic.getAllModelProviders(definition);

        // Assert
        Assert.assertNotNull(allModelProviders);
    }

    @Test
    public void testNullRiskModelsInModules() {
        // Arrange
        Definition definition = new Definition();
        definition.setModules(new HashMap<>());

        // Act
        DefinitionLogic definitionLogic = new DefinitionLogic();
        final List<String> allModelProviders = definitionLogic.getAllModelProviders(definition);

        // Assert
        Assert.assertNull(allModelProviders);
    }

    @Test
    public void testValueInOneValueInRiskModelModules() {
        // Arrange
        Definition definition = new Definition();
        final HashMap<String, Object> modules = new HashMap<>();
        modules.put("riskModels", Arrays.asList("PayU"));
        definition.setModules(modules);

        // Act
        DefinitionLogic definitionLogic = new DefinitionLogic();
        final List<String> allModelProviders = definitionLogic.getAllModelProviders(definition);

        // Assert
        List<String> payUList = Arrays.asList("PayU");
        Assert.assertEquals(payUList, allModelProviders);
    }
}
