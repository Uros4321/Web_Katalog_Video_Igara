import { BrowserRouter, Navigate, Route, Routes, useNavigate } from "react-router-dom"
import "./katalog/KatalogIgara"
import KatalogIgara from "./katalog/KatalogIgara"
import KatalogHome from "./home/KatalogHome"
import KatalogAccount from "./account/KatalogAccount"
import KatalogGame from "./game/KatalogGame"

const CheckAccountPresence = (acc,logg) => {
    const navigate = useNavigate();
    if(logg==true){
        return (
            <div className="w-1/2 h-full">
            <button onClick={() => navigate("/account")} className="bg-gray-400 w-1/2 h-full border-4 border-l-2 border-r-2 border-gray-800">Account</button>
            <button className="bg-gray-400 w-1/2 h-full border-4 border-l-2 border-gray-800">Log Out</button></div>
        )
    }else{
        return (<button className="bg-gray-400 w-1/2 h-full border-4 border-l-2 border-gray-800">Log In</button>)
    }
}

function KatalogMainPage(){
    const navigate = useNavigate();
    var account = {}
    var loggedIn = false

    var dispButtons = CheckAccountPresence(account,loggedIn)

    
    return (
        <div className="bg-[url('https://asset.gecdesigns.com/img/background-templates/dark-blue-background-with-the-diamond-pattern-template-sr06012408-1704699724568-cover.webp')]">
            <nav className="bg-transparent border-gray-200 dark:bg-gray-900  m-0 p-0 h-10">
                <div className="w-2/3 h-full flex flex-wrap items-center justify-between mx-auto  m-0 p-0">
                    <button onClick={() => navigate("/home")} className="bg-gray-400 w-1/4 h-full border-4 border-r-2 border-gray-800">Home</button>
                    <button onClick={() => navigate("/katalog")} className="bg-gray-400 w-1/4 h-full border-4 border-l-2 border-r-2 border-gray-800">Katalog</button>
                    {dispButtons}
            </div>
            </nav>
            <div className="flex justify-center  h-screen">
                
                    <Routes>
                        
                        <Route path="/home" element={<KatalogHome/>}></Route>
                        <Route path="/katalog" element={<KatalogIgara/>}></Route>
                        <Route path="/account" element={<KatalogAccount/>}></Route>
                        <Route path="/game/:id" element={<KatalogGame/>}></Route>
                        <Route path="*" element={<Navigate to={"/home"}/>}></Route>
                    </Routes>
                
            </div>
        </div>
    )
}

export default KatalogMainPage