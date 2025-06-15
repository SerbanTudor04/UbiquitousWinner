package commons;

public enum FileDestionations {
    FILE_SYSTEM,DATABASE;

    @Override
    public String toString() {
        return switch (this) {
            case FILE_SYSTEM -> "filesystem";
            case DATABASE -> "database";
            default -> "unknown";
        };
    }
}
