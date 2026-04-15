import request from "@/axios";

let baseURL = '/article'

export function setFavorite(params) {
    return request({
        url: baseURL + '/setFavorite',
        method: 'post',
        data: params
    })
}

export function selectAllArticles() {
    return request({
        url: baseURL + '/getAllArtiles',
        method: 'get',
    })
}

export function getAllFavorites() {
    return request({
        url: baseURL + '/getAllFavorites',
        method: 'get',
    })
}


export function getOwnCategory() {
    return request({
        url: baseURL + '/getOwnCategory',
        method: 'get',
    })
}

//插入文章
export function insertArticle(params) {
    return request({
        url: baseURL + '/insertArticle',
        method: 'post',
        data: params
    })
}

//更新文章
export function updateArticle(params) {
    return request({
        url: baseURL + '/updateArticle',
        method: 'post',
        data: params
    })
}