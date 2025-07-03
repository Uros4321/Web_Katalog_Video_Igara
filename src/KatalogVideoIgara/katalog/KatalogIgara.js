import KatalogFilter from "./KatalogFilter"
import KatalogSorter from "./KatalogSorter"
import { BrowserRouter, Navigate, Route, Routes, useNavigate } from "react-router-dom"
import GenericService, * as api from "../services/GenericService"
import React, { useState, useEffect } from 'react';

function KatalogIgara() {
    const [data, setData] = useState(null);
    const [displayList, setList] = useState([])
    const [paginationList, setPage] = useState([])
    const [init, initialize] = useState(false)
    const navigate = useNavigate();

    var adresa = "https://127.0.0.1:4430/api/igre"

    var listaIgara = []
    var standardBlockSize = 5
    var compactBlockSize = 20

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


    const filterCallback = (filter_params) => {
        console.log("started_callback")
        console.log(filter_params)
        var filtered = data.filter((igra) => {
            let game_cats = igra.kategorije_igre.map(kategorija => kategorija.id);
            console.log(game_cats)
            let include_fulfilled = filter_params["included"].every((id) => {
                return game_cats.includes(Number(id))
            })
            let exclude_fulfilled = filter_params["excluded"].every((id) => {
                return !game_cats.includes(Number(id))
            })
            let name_fulfilled = igra.naziv.toLowerCase().indexOf(filter_params["name"].toLowerCase())>-1
            console.log(name_fulfilled)

            return include_fulfilled&&exclude_fulfilled&&name_fulfilled
        })
        console.log("filtered")
        console.log(filtered)
        genList(filtered,blockSize,1)
        genPagination(filtered,blockSize,1)
    }
    const genList = (list, blockSize, blockNum) => {
        let p1 = blockSize.toString()
        var prop = "h-1/".concat(p1).concat(" flex flex-row bg-blue border border-4 border-dotted border-blue-500")
        var temp_displayList = []
        let finalNum = blockSize * (blockNum)
        if (finalNum > list.length) {
            finalNum = list.length
        }
        for (let i = blockSize * (blockNum - 1); i < finalNum; i++) {
            temp_displayList.push(
                <li key={list[i].id} className={prop} onClick={() => navigate("/game/" + list[i].id)}><div>{list[i].naziv}</div></li>
            )
        }
        console.log("temp_displayList")
        console.log(temp_displayList)
        setList(temp_displayList)
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
        var temp_paginationList = []
        if (blockNum === 1) {
            temp_paginationList.push(
                <div key={"start"} className={classStr}> {"<<"} </div>

            )
            temp_paginationList.push(
                <div key={"back"} className={classStr}> {"<"} </div>

            )

        } else {
            temp_paginationList.push(
                <button key={"start_button"} className={classStr}> {"<<"} </button>

            )
            temp_paginationList.push(
                <button key={"back_button"} className={classStr}> {"<"} </button>

            )
        }
        for (let i = 0; i < pagenum; i++) {
            if (i === blockNum - 5 - 1) {
                temp_paginationList.push(
                    <button key={"page_"+(i+1)} className={classStr}>{i + 1}</button>
                )
                temp_paginationList.push(
                    <div key={"filler1"} className={classStr}> ... </div>

                )
            }
            if (i === blockNum - 2 - 1 || i === blockNum - 1 - 1 || i === blockNum + 1 - 1 || i === blockNum + 2 - 1) {
                temp_paginationList.push(
                    <button key={"page_"+(i+1)} className={classStr}>{i + 1}</button>
                )
            }
            if (i === blockNum - 1) {
                temp_paginationList.push(
                    <div key={"page_"+(i+1)} className={classStr}>{i + 1}</div>
                )
            }
            if (i === blockNum + 5 - 1) {
                temp_paginationList.push(
                    <div key={"filler2"} className={classStr}> ... </div>

                )
                temp_paginationList.push(
                    <button key={"page_"+(i+1)} className={classStr}>{i + 1}</button>
                )

            }



        }
        if (blockNum === pagenum) {
            temp_paginationList.push(
                <div key={"next"} className={classStr}> {">"} </div>

            )
            temp_paginationList.push(
                <div key={"end"} className={classStr}> {">>"} </div>

            )

        } else {
            temp_paginationList.push(
                <button key={"next_button"} className={classStr}> {">"} </button>

            )
            temp_paginationList.push(
                <button key={"end_button"} className={classStr}> {">>"} </button>

            )
        }
        setPage(temp_paginationList)
    }
    const pageInit = ()=>{
        if(!init){
            console.log("inside block")
            genList(data, blockSize, 1)
            genPagination(data, blockSize, 1)
        }
        initialize(true)
    }
    if (!data) {
        return <p>Loading data...</p>;
    } else {
        if(!init){
            pageInit()
        }
        return (
            <div className="flex flex-col w-2/3 bg-gray-700">
                <div className="flex flex-row h-1/6 w-full bg-blue-950/25">
                    <div className="w-1/4 border border-4 border-dotted border-red-500"><KatalogSorter /></div>
                    <div className="w-3/4 border border-4 border-dotted border-red-500"><KatalogFilter filterCallback={filterCallback}/></div>
                </div>
                <ul className="h-full">
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