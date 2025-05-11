export default{
    dobaviSve(URL_TARGET){
        return fetch(`${URL_TARGET}`,{
            method:"GET",
            headers:{
                "Authorization": localStorage.getItem("token")
            }
        })
    },
    dobaviJedan(URL_TARGET,id){
        return fetch(`${URL_TARGET}/${id}`,{
            method:"GET",
            headers:{
                "Authorization": localStorage.getItem("token")
            }
        })
    },
    dodaj(URL_TARGET,objekat){
        return fetch(`${URL_TARGET}`,{
            method:"POST",
            headers:{
                "Authorization": localStorage.getItem("token")
            },
            body: JSON.stringify(objekat)
        })
    },
    ukloni(URL_TARGET,id){
        return fetch(`${URL_TARGET}/${id}`,{
            method:"DELETE",
            headers:{
                "Authorization": localStorage.getItem("token")
            }
        })
    },
    izmeni(URL_TARGET,objekat,id){
        return fetch(`${URL_TARGET}/${id}`,{
            method:"PUT",
            headers:{
                "Authorization": localStorage.getItem("token")
            },
            body: JSON.stringify(objekat)
        })
    }
}