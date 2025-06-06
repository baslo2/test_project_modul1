package analyzer.utils.validators;

import analyzer.utils.Utils;

public final class RussianValidator implements IValidator {

    @Override
    public boolean isValidKey(int key) {
        return isValidKey(key, Utils.ABC.RUSSIAN_ABC);
    }
}
