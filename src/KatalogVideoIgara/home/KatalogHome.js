function KatalogHome(){
    return (
        <div className="flex flex-col w-2/3 bg-black">
            <div className="h-5/6 flex items-center justify-center w-full text-white justify-center ">
             <p className="text-center text-4xl">Dobrodosli u naš katalog video igara</p>
            </div>
            <div className="h-1/6 w-full bg-blue-950/25 border-slate-950 p-5 text-white border-4 border-dotted border-blue-500">
             <p>Kontakt:</p>
             <p>Adresa: Leva Ulica 57, Žikino Polje</p>
             <p>Telefon: +381 66 7777777</p>
             <p>E-Mail: neki.levi.mail@gmail.com</p>
            </div>
        </div>
    )
}

export default KatalogHome;