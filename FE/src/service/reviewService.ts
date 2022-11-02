
import axios from 'axios';
import { ReviewState } from '../page/product_detail/tab/tab_review/tab_review';
const URL_addReview = 'http://localhost:8080/api/v1/client/review/add';

class reviewService {
    async addReview(data: ReviewState, token: string) {
        return await axios({
            method: "POST",
            data: data,
            url: URL_addReview,
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            }

        })
    }
}
export default new reviewService();