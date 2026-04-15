import axios from "axios";

/**
 * 设置我们的请求的参数
 * @type {string}
 */
axios.defaults.baseURL = "http://121.4.125.31:8099"
// axios.defaults.baseURL = "http://1.12.47.53:8005"

const request = axios.create({
    timeout: 60000,
    headers: {
        'Content-Type': "application/json; charset=utf-8"
    },
})
/**
 * 提前帮助我们设置好我们的请求参数
 */
request.interceptors.request.use((config) => {
    if (config.method === 'post') {
        //设置为json的形式
        config.data = JSON.stringify(config.data);
    }
    return config;
}, (error) => {
    return Promise.reject(error);
});
export default request
