package com.enums.enumerated;

public interface Food {
    enum Appetizer implements Food {
        SALAD, SOUP;
    }
    enum MainCourse implements Food {
        LASAGNE, BURRITO, VINDALO;
    }
    enum Dessert implements Food {
        TIRAMISU, GELATO;
    }
}
