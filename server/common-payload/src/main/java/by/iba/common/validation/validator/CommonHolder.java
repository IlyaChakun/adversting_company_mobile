package by.iba.common.validation.validator;

final class CommonHolder {

    static final String PHONE_PATTERN = "^(\\s*)?(\\+)?([- _():=+]?\\d[- _():=+]?){10,14}(\\s*)?$";

    static final String SITE_PATTERN =
            "((http|https)://)(www.)?[a-zA-Z0-9@:%._\\+~#?&//=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%._\\+~#?&//=]*)";

    private CommonHolder() {

    }
}
