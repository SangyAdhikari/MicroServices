import React, { useState, useEffect } from "react";
import { styled } from "@mui/material/styles";
import MuiDrawer from "@mui/material/Drawer";
import List from "@mui/material/List";
import Divider from "@mui/material/Divider";
import IconButton from "@mui/material/IconButton";
import MenuIcon from "@mui/icons-material/Menu";
import ListItem from "@mui/material/ListItem";
import ListItemButton from "@mui/material/ListItemButton";
import ListItemIcon from "@mui/material/ListItemIcon";
import ListItemText from "@mui/material/ListItemText";
import DashboardIcon from "@mui/icons-material/Dashboard";
import AccountBoxIcon from "@mui/icons-material/AccountBox";
import HistoryEduIcon from "@mui/icons-material/HistoryEdu";
import { Link } from "react-router-dom";
import { useLocation } from "react-router-dom";
import HistoryToggleOffIcon from "@mui/icons-material/HistoryToggleOff";
import EventIcon from "@mui/icons-material/Event";
import "./MuiCss.css";

const drawerWidth = 240;

const openedMixin = (theme) => ({
    width: drawerWidth,
    transition: theme.transitions.create("width", {
        easing: theme.transitions.easing.sharp,
        duration: theme.transitions.duration.enteringScreen,
    }),
    overflowX: "hidden",
});

const closedMixin = (theme) => ({
    transition: theme.transitions.create("width", {
        easing: theme.transitions.easing.sharp,
        duration: theme.transitions.duration.leavingScreen,
    }),
    overflowX: "hidden",
    width: `calc(${theme.spacing(7)} + 1px)`,
    [theme.breakpoints.up("sm")]: {
        width: `calc(${theme.spacing(8)} + 1px)`,
    },
});

const DrawerHeader = styled("div")(({ theme }) => ({
    display: "flex",
    alignItems: "center",
    justifyContent: "flex-end",
    padding: theme.spacing(0, 1),
    // necessary for content to be below app bar
    ...theme.mixins.toolbar,
}));


const Drawer = styled(MuiDrawer, {
    shouldForwardProp: (prop) => prop !== "open",
})(({ theme, open }) => ({
    width: drawerWidth,
    flexShrink: 0,
    whiteSpace: "nowrap",
    boxSizing: "border-box",
    ...(open && {
        ...openedMixin(theme),
        "& .MuiDrawer-paper": openedMixin(theme),
    }),
    ...(!open && {
        ...closedMixin(theme),
        "& .MuiDrawer-paper": closedMixin(theme),
    }),
}));

export default function SideMenuBar({userType}) {
    const location = useLocation();
    const [currentPage, setCurrentPage] = useState(null);

    useEffect(() => {
        setCurrentPage(location.pathname);
    }, [location]);

    const [open, setOpen] = useState(false);


    return (
        <div>
            {
                userType === "User" ?
                    <Drawer className="muidrawer" variant="permanent" open={open} BackdropProps={{ invisible: true }}>
                            <DrawerHeader style={{ marginTop: 60 }}>
                            <IconButton>
                                <MenuIcon onClick={() => setOpen((e) => !e)} className="text-white" />
                            </IconButton>
                        </DrawerHeader>
                        <Divider />
                        <List>
                            <ListItem disablePadding sx={{ display: "block" }}>
                                <ListItemButton
                                    sx={{
                                        minHeight: 48,
                                        justifyContent: open ? "initial" : "center",
                                        px: 2.5,
                                    }}
                                >
                                    <ListItemIcon
                                        sx={{
                                            minWidth: 0,
                                            mr: open ? 0 : "auto",
                                            justifyContent: "center",
                                        }}
                                    >
                                        <Link
                                            onClick={() => setOpen(false)}
                                            to="/home"
                                            className={
                                                currentPage === "/home"
                                                    ? "text-orange text-decoration-non"
                                                    : "text-light text-decoration-none"
                                            }
                                        >
                                            <DashboardIcon />
                                        </Link>
                                    </ListItemIcon>

                                    <ListItemText
                                        primary={
                                            <Link
                                                onClick={() => setOpen(false)}
                                                to="home"
                                                className={
                                                    currentPage === "/home"
                                                        ? "text-orange text-decoration-none px-3"
                                                        : "text-light text-decoration-none  px-3"
                                                }
                                            >
                                                Home
                                            </Link>
                                        }
                                        sx={{ opacity: open ? 1 : 0 }}
                                    />
                                </ListItemButton>
                            </ListItem>

                            <ListItem disablePadding sx={{ display: "block" }}>
                                <ListItemButton
                                    sx={{
                                        minHeight: 48,
                                        justifyContent: open ? "initial" : "center",
                                        px: 2.5,
                                    }}
                                >
                                    <ListItemIcon
                                        sx={{
                                            minWidth: 0,
                                            mr: open ? 0 : "auto",
                                            justifyContent: "center",
                                        }}
                                    >
                                        <Link
                                            onClick={() => setOpen(false)}
                                            to="UpdateProfile"
                                            className={
                                                currentPage === "/UpdateProfile"
                                                    ? "text-orange text-decoration-non"
                                                    : "text-light text-decoration-none"
                                            }
                                        >
                                            <AccountBoxIcon />
                                        </Link>
                                    </ListItemIcon>

                                    <ListItemText
                                        primary={
                                            <Link
                                                onClick={() => setOpen(false)}
                                                to="UpdateProfile"
                                                className={
                                                    currentPage === "/UpdateProfile"
                                                        ? "text-orange text-decoration-none px-3"
                                                        : "text-light text-decoration-none  px-3"
                                                }
                                            >
                                                Update Profile
                                            </Link>
                                        }
                                        sx={{ opacity: open ? 1 : 0 }}
                                    />
                                </ListItemButton>
                            </ListItem>

                            <ListItem disablePadding sx={{ display: "block" }}>
                                <ListItemButton
                                    sx={{
                                        minHeight: 48,
                                        justifyContent: open ? "initial" : "center",
                                        px: 2.5,
                                    }}
                                >
                                    <ListItemIcon
                                        sx={{
                                            minWidth: 0,
                                            mr: open ? 0 : "auto",
                                            justifyContent: "center",
                                        }}
                                    >
                                        <Link
                                            onClick={() => setOpen(false)}
                                            to="userhistory"
                                            className={
                                                currentPage === "/userhistory"
                                                    ? "text-orange text-decoration-non"
                                                    : "text-light text-decoration-none"
                                            }
                                        >
                                            <HistoryEduIcon />
                                        </Link>
                                    </ListItemIcon>

                                    <ListItemText
                                        primary={
                                            <Link
                                                onClick={() => setOpen(false)}
                                                to="userhistory"
                                                className={
                                                    currentPage === "/userhistory"
                                                        ? "text-orange text-decoration-none px-3"
                                                        : "text-light text-decoration-none  px-3"
                                                }
                                            >
                                                User History
                                            </Link>
                                        }
                                        sx={{ opacity: open ? 1 : 0 }}
                                    />
                                </ListItemButton>
                            </ListItem>
                        </List>
                        <Divider />
                    </Drawer>
                    :
                    <>


                        <Drawer className="muidrawer" variant="permanent" open={open} BackdropProps={{ invisible: true }}>
                            <DrawerHeader style={{ marginTop: 60 }}>
                                <IconButton>
                                    <MenuIcon onClick={() => setOpen((e) => !e)} className="text-white" />
                                </IconButton>
                            </DrawerHeader>
                            <Divider />
                            <List>
                                <ListItem disablePadding sx={{ display: "block" }}>
                                    <ListItemButton
                                        sx={{
                                            minHeight: 48,
                                            justifyContent: open ? "initial" : "center",
                                            px: 2.5,
                                        }}
                                    >
                                        <ListItemIcon
                                            sx={{
                                                minWidth: 0,
                                                mr: open ? 0 : "auto",
                                                justifyContent: "center",
                                            }}
                                        >
                                            <Link
                                                onClick={() => setOpen(false)}
                                                to="home"
                                                className={
                                                    currentPage === "/home"
                                                        ? "text-orange text-decoration-non"
                                                        : "text-light text-decoration-none"
                                                }
                                            >
                                                <DashboardIcon />
                                            </Link>
                                        </ListItemIcon>

                                        <ListItemText
                                            primary={
                                                <Link
                                                    onClick={() => setOpen(false)}
                                                    to="home"
                                                    className={
                                                        currentPage === "/home"
                                                            ? "text-orange text-decoration-none px-3"
                                                            : "text-light text-decoration-none  px-3"
                                                    }
                                                >
                                                    Home
                                                </Link>
                                            }
                                            sx={{ opacity: open ? 1 : 0 }}
                                        />
                                    </ListItemButton>
                                </ListItem>

                                <ListItem disablePadding sx={{ display: "block" }}>
                                    <ListItemButton
                                        sx={{
                                            minHeight: 48,
                                            justifyContent: open ? "initial" : "center",
                                            px: 2.5,
                                        }}
                                    >
                                        <ListItemIcon
                                            sx={{
                                                minWidth: 0,
                                                mr: open ? 0 : "auto",
                                                justifyContent: "center",
                                            }}
                                        >
                                            <Link
                                                onClick={() => setOpen(false)}
                                                to="ShowsHistory"
                                                className={
                                                    currentPage === "/ShowsHistory"
                                                        ? "text-orange text-decoration-non"
                                                        : "text-light text-decoration-none"
                                                }
                                            >
                                                <HistoryToggleOffIcon />
                                            </Link>
                                        </ListItemIcon>

                                        <ListItemText
                                            primary={
                                                <Link
                                                    onClick={() => setOpen(false)}
                                                    to="ShowsHistory"
                                                    className={
                                                        currentPage === "/ShowsHistory"
                                                            ? "text-orange text-decoration-none px-3"
                                                            : "text-light text-decoration-none  px-3"
                                                    }
                                                >
                                                    Show History
                                                </Link>
                                            }
                                            sx={{ opacity: open ? 1 : 0 }}
                                        />
                                    </ListItemButton>
                                </ListItem>

                                <ListItem disablePadding sx={{ display: "block" }}>
                                    <ListItemButton
                                        sx={{
                                            minHeight: 48,
                                            justifyContent: open ? "initial" : "center",
                                            px: 2.5,
                                        }}
                                    >
                                        <ListItemIcon
                                            sx={{
                                                minWidth: 0,
                                                mr: open ? 0 : "auto",
                                                justifyContent: "center",
                                            }}
                                        >
                                            <Link
                                                onClick={() => setOpen(false)}
                                                to="TheaterList"
                                                className={
                                                    currentPage === "/TheaterList"
                                                        ? "text-orange text-decoration-non"
                                                        : "text-light text-decoration-none"
                                                }
                                            >
                                                <EventIcon />
                                            </Link>
                                        </ListItemIcon>

                                        <ListItemText
                                            primary={
                                                <Link
                                                    onClick={() => setOpen(false)}
                                                    to="TheaterList"
                                                    className={
                                                        currentPage === "/TheaterList"
                                                            ? "text-orange text-decoration-none px-3"
                                                            : "text-light text-decoration-none  px-3"
                                                    }
                                                >
                                                    Venue Details Update
                                                </Link>
                                            }
                                            sx={{ opacity: open ? 1 : 0 }}
                                        />
                                    </ListItemButton>
                                </ListItem>

                                <ListItem disablePadding sx={{ display: "block" }}>
                                    <ListItemButton
                                        sx={{
                                            minHeight: 48,
                                            justifyContent: open ? "initial" : "center",
                                            px: 2.5,
                                        }}
                                    >
                                        <ListItemIcon
                                            sx={{
                                                minWidth: 0,
                                                mr: open ? 0 : "auto",
                                                justifyContent: "center",
                                            }}
                                        >
                                            <Link
                                                onClick={() => setOpen(false)}
                                                to="UpdateProfile"
                                                className={
                                                    currentPage === "/UpdateProfile"
                                                        ? "text-orange text-decoration-non"
                                                        : "text-light text-decoration-none"
                                                }
                                            >
                                                <AccountBoxIcon />
                                            </Link>
                                        </ListItemIcon>

                                        <ListItemText
                                            primary={
                                                <Link
                                                    onClick={() => setOpen(false)}
                                                    to="UpdateProfile"
                                                    className={
                                                        currentPage === "/UpdateProfile"
                                                            ? "text-orange text-decoration-none px-3"
                                                            : "text-light text-decoration-none  px-3"
                                                    }
                                                >
                                                    Update Profile
                                                </Link>
                                            }
                                            sx={{ opacity: open ? 1 : 0 }}
                                        />
                                    </ListItemButton>
                                </ListItem>
                            </List>
                            <Divider />
                        </Drawer>
                    </>
            }
        </div>


    );
}
