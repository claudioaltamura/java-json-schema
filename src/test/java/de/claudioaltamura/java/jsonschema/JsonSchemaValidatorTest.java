package de.claudioaltamura.java.jsonschema;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JsonSchemaValidatorTest {

  private JsonSchema schema;

  @BeforeEach
  public void setUp() throws IOException, ProcessingException {
    JsonNode productSchema = JsonLoader.fromResource("/product.schema.json");
    JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
    schema = factory.getJsonSchema(productSchema);
  }

  @Test
  @Disabled
  @DisplayName("invalid input when validating then invalid")
  void givenInvalidInput_whenValidating_thenInvalid() throws IOException, ProcessingException {
    final JsonNode product = JsonLoader.fromResource("/product-invalid.json");

    assertThrows(ProcessingException.class, () -> schema.validate(product));
  }

  @Test
  @DisplayName("valid input when validating then valid")
  void givenValidInput_whenValidating_thenValid() throws IOException {
    final JsonNode product = JsonLoader.fromResource("/product.json");

    assertDoesNotThrow(() -> schema.validate(product));
  }
}
