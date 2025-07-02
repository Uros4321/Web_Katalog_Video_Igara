import GenericService, * as api from "../services/GenericService"
import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router';

function KatalogGame() {

    const [data, setData] = useState(null);


    var catList = []
    var platList = []
    var adresa = "https://127.0.0.1:4430/api/igre"
    var params = useParams();
    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await GenericService.dobaviJedan(adresa, params.id);
                const result = await response.json();
                setData(result);
            } catch (error) {
                console.error("Error fetching data:", error);
            }
        };
        fetchData();
    }, []);

    if (!data) {
        return <p>Loading data...</p>;
    } else {
        data.kategorije_igre.forEach(kat => {
            catList.push(kat.naziv)
        });
        data.platforme_igre.forEach(plat => {
            platList.push(plat.proizvodjac + " " + plat.naziv)
        })
        return (

            <div className="flex flex-col w-2/3 h-full bg-gray-700">
                <div className="flex flex-row h-1/4 border border-2 border-dashed border-blue-500">
                    <div className="flex w-1/5 h-full border border-2 border-dashed border-blue-500">img</div>
                    <div className="flex w-full h-full border border-2 border-dashed border-blue-500">{data.naziv}</div>
                </div>
                <div className="flex flex-row h-1/6 border border-2 border-dashed border-blue-500">
                    {data.opis}
                </div>
                <div className="flex flex-row h-1/10 border border-2 border-dashed border-blue-500">
                    <div className="flex w-1/2 h-full border border-2 border-dashed border-blue-500">{data.izdavacka_kuca}</div>
                    <div className="flex w-1/2 h-full border border-2 border-dashed border-blue-500">{data.godina_izdanja}</div>
                </div>
                <div className="flex flex-row h-1/6 border border-2 border-dashed border-blue-500">
                    {catList.toString()}
                </div>
                <div className="flex flex-row h-1/6 border border-2 border-dashed border-blue-500">
                    {platList.toString()}
                </div>
                <div className="flex flex-row h-full border border-2 border-dashed border-blue-500">
                    komentari
                </div>
            </div>

        )
    }
    // useEffect(() => {
    //     async function fetchData() {
    //         try {
    //             const response = await GenericService.dobaviJedan(adresa, params.id);
    //             if (!response.ok) {
    //                 throw new Error(`HTTP error! status: ${response.status}`);
    //             }
    //             const result = await response.json();
    //             console.log(result)
    //             result.kategorije_igre.forEach(kat => {
    //                 catList.push(kat.naziv)
    //             });
    //             result.platforme_igre.forEach(plat => {
    //                 platList.push(plat.proizvodjac + " " + plat.naziv)
    //             })
    //             setData(result);
    //         } catch (err) {
    //             setError(err);
    //         } finally {
    //             setLoading(false);
    //         }
    //     }
    //     fetchData();
    // }, []);







    // console.log(catList)

    // return (
    //     <div>
    //         <div className="flex flex-col w-2/3 h-full bg-gray-700">
    //             <div className="flex flex-row h-1/4 border border-2 border-dashed border-blue-500">
    //                 <div className="flex w-1/5 h-full border border-2 border-dashed border-blue-500">img</div>
    //                 <div className="flex w-full h-full border border-2 border-dashed border-blue-500">{data.naziv}</div>
    //             </div>
    //             <div className="flex flex-row h-1/6 border border-2 border-dashed border-blue-500">
    //                 {data.opis}
    //             </div>
    //             <div className="flex flex-row h-1/10 border border-2 border-dashed border-blue-500">
    //                 <div className="flex w-1/2 h-full border border-2 border-dashed border-blue-500">{data.izdavacka_kuca}</div>
    //                 <div className="flex w-1/2 h-full border border-2 border-dashed border-blue-500">{data.godina_izdanja}</div>
    //             </div>
    //             <div className="flex flex-row h-1/6 border border-2 border-dashed border-blue-500">
    //                 {catList.toString()}
    //             </div>
    //             <div className="flex flex-row h-1/6 border border-2 border-dashed border-blue-500">
    //                 {platList.toString()}
    //             </div>
    //             <div className="flex flex-row h-full border border-2 border-dashed border-blue-500">
    //                 komentari
    //             </div>
    //         </div>
    //         <pre>{JSON.stringify(data, null, 2)}</pre>
    //     </div>
    // )
}

export default KatalogGame