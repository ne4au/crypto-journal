import { FC } from 'react';
import styles from '../../styles';

interface FeatureCardProps {
    icon : string,
    title : string,
    content : string,
    isLast : boolean,
}

const FeatureCard : FC<FeatureCardProps> = ({ icon, title, content, isLast }) => (
    <div className={`flex flex-row p-6 w-full rounded-[20px] ${!isLast ? "mb-6" : "mb-0"} feature-card`}>
        <div className={`w-[64px] h-[64px] rounded-full ${styles.flexCenter} bg-dimBlue`}>
            <img src={icon} alt="star" className="w-[50%] h-[50%] object-contain" />
        </div>
        <div className="flex-1 flex flex-col ml-3">
            <h4 className="font-poppins font-semibold text-white text-[18px] leading-[23.4px] mb-1">
                {title}
            </h4>
            <p className="font-poppins font-normal text-dimWhite text-[16px] leading-[24px]">
                {content}
            </p>
        </div>
    </div>
);

export default FeatureCard;