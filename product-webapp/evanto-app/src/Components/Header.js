import React from 'react'

export default function Header() {
    return (
        <div className='bg-warning'>
        <div className='container'>
            <nav className="navbar navbar-light ">
                <div className="container-fluid">
                    <a href='#' className="navbar-brand">My Ticket</a>
                    <form className="d-flex">
                        <input className="form-control me-2" type="search" placeholder="Search" aria-label="Search" />
                        <button className="btn btn-outline-success" type="submit">Search</button>
                    </form>
                </div>
            </nav>



        </div>
        </div>
    )
}
