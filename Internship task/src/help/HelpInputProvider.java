package help;

public class HelpInputProvider implements InputProvider {
    // simulates system input for testing

    private final String[] inputs;
    private int index;

    public HelpInputProvider(String... inputs) {
        this.inputs = inputs;
        this.index = 0;
    }

    @Override
    public String getInput() {
        if (index < inputs.length) {
            return inputs[index++];
        }
        return "";
    }
}
