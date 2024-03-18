package com.fpis.fontazija.kokteli.dto;

import java.util.List;

public class PaginatedKoktelListResponse {

    private List<KoktelsListResponse> koktelsListResponses;
    private int totalPages;

    public PaginatedKoktelListResponse(List<KoktelsListResponse> koktelsListResponses, int totalPages) {
        this.koktelsListResponses = koktelsListResponses;
        this.totalPages = totalPages;
    }

    public List<KoktelsListResponse> getKoktelsListResponses() {
        return koktelsListResponses;
    }

    public void setKoktelsListResponses(List<KoktelsListResponse> koktelsListResponses) {
        this.koktelsListResponses = koktelsListResponses;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
