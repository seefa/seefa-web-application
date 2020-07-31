package ir.seefa.web.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Saman Delfani
 * @version 1.0
 * @since 23 Jul 2020 T 12:43:53
 */
@Entity
@Table(name = "Language", schema = "seefasitev1", catalog = "")
public class LanguageEntity {
    private int langId;
    private String langTitle;

    @Id
    @Column(name = "lang_id", nullable = false)
    public int getLangId() {
        return langId;
    }

    public void setLangId(int langId) {
        this.langId = langId;
    }

    @Basic
    @Column(name = "lang_title", nullable = false, length = 30)
    public String getLangTitle() {
        return langTitle;
    }

    public void setLangTitle(String langTitle) {
        this.langTitle = langTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LanguageEntity that = (LanguageEntity) o;
        return langId == that.langId &&
                Objects.equals(langTitle, that.langTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(langId, langTitle);
    }
}
