package test_data.model;

public enum Category {
    APPLIANCES("Appliances"),
    VEHICLES("Vehicles"),
    WINE("Wine");

    private String value;
    Category(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public Category getCategory(String value) {
        Category[] categories = Category.values();
        for (Category category : categories) {
            if (category.value().equals(value)) {
                return category;
            }
        }
        return null;
    }
}
