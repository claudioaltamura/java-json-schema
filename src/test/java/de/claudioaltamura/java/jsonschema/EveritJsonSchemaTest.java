package de.claudioaltamura.java.jsonschema;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EveritJsonSchemaTest {

  private JSONObject jsonSchema;

  @BeforeEach
  public void beforeAll() {
    jsonSchema =
        new JSONObject(
            new JSONTokener(
                EveritJsonSchemaTest.class.getResourceAsStream("/product.schema.json")));
  }

  @Test
  @DisplayName("invalid input when validating then invalid")
  void givenInvalidInput_whenValidating_thenInvalid() throws ValidationException {
    JSONObject jsonSubject =
        new JSONObject(
            new JSONTokener(
                EveritJsonSchemaTest.class.getResourceAsStream("/product-invalid.json")));

    Schema schema = SchemaLoader.load(jsonSchema);

    assertThrows(ValidationException.class, () -> schema.validate(jsonSubject));
  }

  @Test
  @DisplayName("valid input when validating then valid")
  void givenValidInput_whenValidating_thenValid() throws ValidationException {
    JSONObject jsonSubject =
        new JSONObject(
            new JSONTokener(EveritJsonSchemaTest.class.getResourceAsStream("/product.json")));

    Schema schema = SchemaLoader.load(jsonSchema);

    assertDoesNotThrow(() -> schema.validate(jsonSubject));
  }
}
