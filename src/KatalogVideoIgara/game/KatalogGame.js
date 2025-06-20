import * as api from "../services/GenericService"
import React from 'react';
import { useParams } from 'react-router';

function KatalogGame() {
    var adresa = "https://127.0.0.1:8443/api/igre"
    const params = useParams();
    var id = params.id
    console.log(id)
    var igra = {
        "id": 7,
        "kategorije_igre": [
            {
                "id": 2,
                "naziv": "Action",
                "igre_u_kategoriji": null
            },
            {
                "id": 1,
                "naziv": "RPG",
                "igre_u_kategoriji": null
            }
        ],
        "platforme_igre": [
            {
                "id": 3,
                "naziv": "Genesis",
                "tip_platforme": "konzola",
                "proizvodjac": "Saga"
            },
            {
                "id": 2,
                "naziv": "SNGS",
                "tip_platforme": "konzola",
                "proizvodjac": "Nontendo"
            }
        ],
        "naziv": "Igra2",
        "izdavacka_kuca": "kuca1",
        "opis": "novi_Opis_1",
        "godina_izdanja": "2002"
    }
    var catList =[] 
    igra.kategorije_igre.forEach(kat => {
        catList.push(kat.naziv)
    });
    var platList = []
    igra.platforme_igre.forEach(plat=>{
        platList.push(plat.proizvodjac+" "+plat.naziv)
    })
    console.log(catList)

    return (
        <div className="flex flex-col w-2/3 h-full bg-gray-700">
            <div className="flex flex-row h-1/4 border border-2 border-dashed border-blue-500">
                <div className="flex w-1/5 h-full border border-2 border-dashed border-blue-500">img</div>
                <div className="flex w-full h-full border border-2 border-dashed border-blue-500">{igra.naziv}</div>
            </div>
            <div className="flex flex-row h-1/6 border border-2 border-dashed border-blue-500">
                {igra.opis}
            </div>
            <div className="flex flex-row h-1/10 border border-2 border-dashed border-blue-500">
                <div className="flex w-1/2 h-full border border-2 border-dashed border-blue-500">{igra.izdavacka_kuca}</div>
                <div className="flex w-1/2 h-full border border-2 border-dashed border-blue-500">{igra.godina_izdanja}</div>
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

export default KatalogGame