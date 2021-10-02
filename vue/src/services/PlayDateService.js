import axios from 'axios'

export default{ 
    createPlayDate(playDate) {
        return axios.post('/pet/createPlayDate', playDate);
    },
    getCurrentDate(){
        return axios.get('/pet/createPlayDate');
    },
    getAllPlayDates(){
        return axios.get('/pet/allPlayDates/');
    },
    getAvailablePlayDates(id) {
        return axios.get(`/pet/allPlayDates/available/${id}`);
    },
    getAvailablePlayDatesByPlayDateId(playDateId){
        return axios.get(`pet/playDate/${playDateId}`);
    },
    createRequest(request) {
        return axios.post(`/pet/request`, request);
    },
    getMyRequestsToApprove(id){
        return axios.get(`/pet/myRequest/${id}`)
    },
    updateRequest(playDateId, mateId) {
        return axios.put(`/pet/request/${playDateId}/${mateId}`)
    },
    getAllConfirmedPlayDates(userId) {
        return axios.get(`/pet/playDates/${userId}`)
    },
    getPendingPlayDates(userId) {
        return axios.get(`pet/playDatesPending/${userId}`)
    },
    cancelPlayDate(playDateId) {
        return axios.put(`/pet/playDates/cancel/${playDateId}`);
    }
}