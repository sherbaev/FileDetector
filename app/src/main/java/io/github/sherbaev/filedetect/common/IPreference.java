package io.github.sherbaev.filedetect.common;

public interface IPreference {

    void saveVersion(int version);

    int loadVersion();


}
