package service;

import service.impl.ChapterServiceImpl;

public interface ChapterService {
    static ChapterService getInstance() { return ChapterServiceImpl.getInstance(); }
}
