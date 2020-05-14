package de.claudioaltamura.java.jsonschema;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion.VersionFlag;
import com.networknt.schema.ValidationMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @see <a href="https://github.com/networknt/json-schema-validator>networknt
 *     json-schema-validator</a>
 */
class NetworkNtJsonSchemaValidatorTest {

  private ObjectMapper mapper = new ObjectMapper();
  private JsonSchema schema;

  @BeforeEach
  public void beforeAll() {
    JsonSchemaFactory factory = JsonSchemaFactory.getInstance(VersionFlag.V4);
    InputStream is =
        NetworkNtJsonSchemaValidatorTest.class.getResourceAsStream("/product.schema.json");
    schema = factory.getSchema(is);
  }

  @Test
  @DisplayName("invalid input when validating then invalid with details")
  void givenInvalidInput_whenValidating_thenInvalid_With_Details() throws IOException {
    JsonNode node =
        mapper.readTree(
            NetworkNtJsonSchemaValidatorTest.class.getResourceAsStream("/product-invalid.json"));

    Set<ValidationMessage> errors = schema.validate(node);
    assertThat(errors.size()).isEqualTo(1);
    assertThat(errors.iterator().next().getMessage())
        .contains("price: must have a minimum value of 0");
  }

  @Test
  @DisplayName("valid input when validating then valid")
  void givenValidInput_whenValidating_thenValid() throws IOException {
    JsonNode node =
        mapper.readTree(
            NetworkNtJsonSchemaValidatorTest.class.getResourceAsStream("/product.json"));

    Set<ValidationMessage> errors = schema.validate(node);
    assertThat(errors.size()).isEqualTo(0);
  }
}
