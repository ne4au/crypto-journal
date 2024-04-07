import {star, shield, collect } from '../../assets';

export const navLinks : Array<{
    id: string,
    title: string
}> = [
    {
        id: "home",
        title: "Home",
      },
      {
        id: "about_us",
        title: "About Us",
      },
      {
        id: "product",
        title: "Product",
      },
      {
        id: "clients",
        title: "Clients",
      },
]

export const stats = [
  {
    id: "stats-1",
    title: "User Active",
    value: "3800+",
  },
  {
    id: "stats-2",
    title: "Total views",
    value: "40k+",
  },
  {
    id: "stats-3",
    title: "Transactions",
    value: "120k+",
  },
];


export const features = [
  {
    id: "feature-1",
    icon: star,
    title: "Rewards",
    content:
      "The best credit cards offer some tantalizing combinations of promotions and prizes",
  },
  {
    id: "feature-2",
    icon: shield,
    title: "100% Secured",
    content:
      "We take proactive steps make sure your information and transactions are secure.",
  },
  {
    id: "feature-3",
    icon: collect,
    title: "Balance Transfer",
    content:
      "A balance transfer credit card can save you a lot of money in interest charges.",
  },
];