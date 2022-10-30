
import axios from 'axios';
const URL_getAllFormatActive = 'http://localhost:8080/api/v1/format/active/all';

class formatService {


    async getActiveAllFormat() {
        return await axios({
            method: "GET",
            url: URL_getAllFormatActive,
        })
    }

}
export default new formatService();