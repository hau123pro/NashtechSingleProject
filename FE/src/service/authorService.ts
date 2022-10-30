
import axios from 'axios';
const URL_getAllAuthorActive = 'http://localhost:8080/api/v1/author/active/all';

class authorService {
    async getActiveAllAuthor() {
        return await axios({
            method: "GET",
            url: URL_getAllAuthorActive,
        })
    }
}
export default new authorService();