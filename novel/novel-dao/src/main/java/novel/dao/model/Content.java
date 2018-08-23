package novel.dao.model;

public class Content {
    private String catalog;

    private String catalogUUID;

    private String text;


    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getCatalogUUID() {
        return catalogUUID;
    }

    public void setCatalogUUID(String catalogUUID) {
        this.catalogUUID = catalogUUID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}