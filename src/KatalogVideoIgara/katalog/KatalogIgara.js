import KatalogFilter from "./KatalogFilter"
import KatalogSorter from "./KatalogSorter"
import { BrowserRouter, Navigate, Route, Routes, useNavigate } from "react-router-dom"
import GenericService, * as api from "../services/GenericService"
import React, { useState, useEffect } from 'react';

function KatalogIgara() {
    const [data, setData] = useState(null);
    const navigate = useNavigate();

    var adresa = "https://127.0.0.1:4430/api/igre"
    var adresa_cat = "https://127.0.0.1:4430/api/kategorije"

    var listaIgara = []
    var standardBlockSize = 5
    var compactBlockSize = 20
    var displayList = []
    var paginationList = []

    var blockSize = compactBlockSize

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await GenericService.dobaviSve(adresa);
                const result = await response.json();
                setData(result);
            } catch (error) {
                console.error("Error fetching data:", error);
            }
        };
        fetchData();
    }, []);


    const genList = (list, blockSize, blockNum) => {
        let p1 = blockSize.toString()
        var prop = "h-1/".concat(p1).concat(" flex flex-row bg-blue border border-4 border-dotted border-blue-500")
        console.log(prop)
        let finalNum = blockSize * (blockNum)
        if (finalNum > list.length) {
            finalNum = list.length
        }
        for (let i = blockSize * (blockNum - 1); i < finalNum; i++) {
            displayList.push(
                <li className={prop} onClick={() => navigate("/game/"+list[i].id)}><div>{list[i].naziv}</div></li>
            )
        }
    }
    const genPagination = (list, blockSize, blockNum) => {
        let whole = Math.floor(list.length / blockSize)
        let totalInWhole = whole * blockSize
        let remain = list.length - totalInWhole
        let pagenum = whole
        let classStr = "w-full flex flex-col items-center text-center justify-center border border-2 border-dashed border-green-500"
        if (remain > 0) {
            pagenum += 1
        }
        paginationList = []
        if (blockNum === 1) {
            paginationList.push(
                <div className={classStr}> {"<<"} </div>

            )
            paginationList.push(
                <div className={classStr}> {"<"} </div>

            )

        } else {
            paginationList.push(
                <button className={classStr}> {"<<"} </button>

            )
            paginationList.push(
                <button className={classStr}> {"<"} </button>

            )
        }
        for (let i = 0; i < pagenum; i++) {
            if (i === blockNum - 5 - 1) {
                paginationList.push(
                    <button className={classStr}>{i + 1}</button>
                )
                paginationList.push(
                    <div className={classStr}> ... </div>

                )
            }
            if (i === blockNum - 2 - 1 || i === blockNum - 1 - 1 || i === blockNum + 1 - 1 || i === blockNum + 2 - 1) {
                paginationList.push(
                    <button className={classStr}>{i + 1}</button>
                )
            }
            if (i === blockNum - 1) {
                paginationList.push(
                    <div className={classStr}>{i + 1}</div>
                )
            }
            if (i === blockNum + 5 - 1) {
                paginationList.push(
                    <div className={classStr}> ... </div>

                )
                paginationList.push(
                    <button className={classStr}>{i + 1}</button>
                )

            }



        }
        if (blockNum === pagenum) {
            paginationList.push(
                <div className={classStr}> {">"} </div>

            )
            paginationList.push(
                <div className={classStr}> {">>"} </div>

            )

        } else {
            paginationList.push(
                <button className={classStr}> {">"} </button>

            )
            paginationList.push(
                <button className={classStr}> {">>"} </button>

            )
        }
    }
    if (!data) {
        return <p>Loading data...</p>;
    } else {
        genList(data, blockSize, 1)
        genPagination(data, blockSize, 1)
        return (
            <div className="flex flex-col w-2/3 bg-gray-700">
                <div className="flex flex-row h-1/6 w-full bg-blue-950/25">
                    <div className="w-1/4 border border-4 border-dotted border-red-500"><KatalogSorter /></div>
                    <div className="w-3/4 border border-4 border-dotted border-red-500"><KatalogFilter /></div>
                </div>
                <ul className="h-full">

                    {/* <li className="h-1/5 flex flex-row"><div className="w-1/5 bg-blue-600">game1 image</div><div> game1 description</div></li>
                <li className="h-1/5 flex flex-row"><div className="w-1/5 bg-blue-600">game2 image</div><div> game2 description</div></li>
                <li className="h-1/5 flex flex-row"><div className="w-1/5 bg-blue-600">game3 image</div><div> game3 description</div></li>
                <li className="h-1/5 flex flex-row"><div className="w-1/5 bg-blue-600">game4 image</div><div> game4 description</div></li>
                <li className="h-1/5 flex flex-row"><div className="w-1/5 bg-blue-600">game5 image</div><div> game5 description</div></li> */}
                    {displayList}
                </ul>
                <div className="h-1/6 w-full flex flex-row justify-stretch bg-blue-950/25">
                    <div className="w-full"></div>
                    {paginationList}
                    <div className="w-full"></div>
                </div>
            </div>
        )
    }

}

export default KatalogIgara