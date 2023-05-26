import React from 'react'

export default function ExtraMenu() {
    return (

            <nav className="navbar navbar-expand-lg navbar-light bg-light">
                <div className="container">
                    <h5 className="navbar-brand" href="#">Offers</h5>
                    <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"></span>
                    </button>
                    <div className="collapse navbar-collapse" id="navbarNav">
                        <ul className="navbar-nav">
                            <li className="nav-item">
                                <h5 className="nav-link" href="#">Movies<sup className='p-1 bg-danger rounded text-white' style={{fontSize: 8}}>NEW</sup></h5>
                            </li>
                            <li className="nav-item">
                                <h5 className="nav-link" href="#">Sports</h5>
                            </li>
                            <li className="nav-item">
                                <h5 className="nav-link" href="#">Events</h5>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
    )
}
