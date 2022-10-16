package br.com.cwi.crescer.oldflix.model;

public enum Category {
    BRONZE(5),
    PRATA(3),
    OURO(2);

    private int descricao;

    Category(int descricao) {
        this.descricao=descricao;
    }

    public int getDescricao() {
        return descricao;
    }
}
