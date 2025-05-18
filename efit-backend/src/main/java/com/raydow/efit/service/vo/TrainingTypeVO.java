package com.raydow.efit.service.vo;

public class TrainingTypeVO {

    private Integer id;
    private String name;

    public TrainingTypeVO() {
    }

    private TrainingTypeVO(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer id;
        private String name;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public TrainingTypeVO build() {
            return new TrainingTypeVO(this);
        }
    }
}
