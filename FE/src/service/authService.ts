
import axios from 'axios';
const URL_getAllAuthorActive = 'http://localhost:8080/api/v1/author/active/all';

class authService {
    getToken() {
        return localStorage.getItem('token');
    }
}
export default new authService();