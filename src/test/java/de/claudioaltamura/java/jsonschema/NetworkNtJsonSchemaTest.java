package de.claudioaltamura.java.jsonschema;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion.VersionFlag;
import com.networknt.schema.ValidationMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import org.everit.json.schema.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @see <a href="https://github.com/networknt/json-schema-validator>networknt json-schema-validator</a>
 */
class NetworkNtJsonSchemaTest {

  @Test
  @DisplayName("valid input when validating then valid")
  void givenValidInput_whenValidating_thenValid() throws ValidationException, IOException {
    JsonSchemaFactory factory = JsonSchemaFactory.getInstance(VersionFlag.V4);
    InputStream is = NetworkNtJsonSchemaTest.class.getResourceAsStream("/product.schema.json");
    JsonSchema schema = factory.getSchema(is);

    ObjectMapper mapper = new ObjectMapper();
    JsonNode node = mapper.readTree(NetworkNtJsonSchemaTest.class.getResourceAsStream("/product.json"));

    Set<ValidationMessage> errors = schema.validate(node);
    assertThat(errors.size()).isEqualTo(0);
  }
}
